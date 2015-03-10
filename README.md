# Pocket

This project is all about a _Proof-of-Concept_ (POC) for the AppliedDL system. The
initial work is on the generators for data to run into the analysis, and then there
will be the analysis that will "find" the signal in the data set, and verify it.
For now, we have several tools for building up these data sets.

## Generators

The concept of the _Generator_ is a simple random number generator - but with a
very specific set of parameters and a specific probability distribution function.

### Uniform Distribution

The standard uniform distribution is an infinite sequence created with:
```clojure
(uniform lo hi)
```
where the limits are _optional_ and default to 0 and 1. The lower limit is
_inclusive_ and the upper limit is _exclusive_ - as you might expect with any
uniform distribution.

It's important - as with all the generators to _limit_ the data to some finite
sequence or you'll have a nasty infinite loop on your hands.

```clojure
  => (take 5 (uniform -1 1))
    (-0.7765801046493335 0.40148224293121815 0.8737800042586443
     -0.341621481583122 0.4214790667583346)
```

### Normal Distribution

The standard normal (Gaussian) distribution is an infinite sequence created with:
```clojure
(normal mean stdev)
```
where the parameters are _optional_ and default to 0 and 1.

It's important - as with all the generators to _limit_ the data to some finite
sequence or you'll have a nasty infinite loop on your hands.

```clojure
  => (take 5 (normal))
    (-0.0907230678156977 0.3229645010043943 0.7032449896635354
     0.3105866707525134 0.5179258261515017)
```

### Log-Normal Distribution

The standard log-normal distribution is an infinite sequence created where:
```
v = exp(mean + stdev * x)
```
where `x` is the Gaussian random variable with a zero mean and a standard
deviation of 1.

In clojure this is done with:
```clojure
(log-normal mean stdev)
```
where the parameters are _optional_ and default to 0 and 1.

It's important - as with all the generators to _limit_ the data to some finite
sequence or you'll have a nasty infinite loop on your hands.

```clojure
  => (take 5 (log-normal 3 1))
    (19.324672878464074 25.44649659534456 19.66209965402554
     22.365682296366312 32.87615573609718)
```

### Exponential Distribution

The exponential distribution is an infinite sequence created where:
```
v = ln(1 - x)/(-lambda)
```
where `x` is uniform random variable on [0, 1) and `lambda` is the _rate parameter_
for the distribution.

In clojure this is done with:
```clojure
(exponential lambda)
```
where the parameter is _**required**_ and must be positive.

It's important - as with all the generators to _limit_ the data to some finite
sequence or you'll have a nasty infinite loop on your hands.

```clojure
  => (take 5 (exponential 5))
    (0.22165641946479245 0.12155878618865423 0.06810312651993966
     0.21170065909517813 0.2798397169892709)
```

### Mapped Distributions

While these mathematical random variables are very good as a start, there will
be times that you want to create a new random variable based on the _output_
of one of these, and specifically tailor the mapping from one of these base
random variable generators to your desired application. This is the reason
for the `mapped` function.

The `mapped` function also generates an infinite sequence of values - just
like all the other generators in the `base` namespace, but this one does so
by taking, as input, another generator, and a map of how to map the output
of that generator into the output of the `mapped` generator.

For instance, say I wanted to have an unfair coin toss. I could do that with
any number of schemes, but the `mapped` function makes that easy:
```clojure
;; define a uniform random variable from 0 to 100
(def und (uniform 0 100))
;; define the mapping of the output
(def cov {:heads [0 55], :tails [55 100]})
;; now define the mapped random sequence
(def bad-penny (mapped und cov))

  => (frequencies (take 1000 bad-penny))
    {:heads 552, :tails 448}
```

## The Basic Actors

The goal is to model a company's overall sales pipeline - and what might
need to be changed to make it more successful, the first phase is to model
the typical company with a sales staff, deals, and a well-defined sales
pipeline. To this end, there will be more complex _Generators_ and these
will be the Sales Rep, and the Deal.

### The Sales Pipeline

The starting point for all this is the _Sales Pipeline_ - that series of
steps that every deal much go through from first contact (cold call) to
the final inking of the deal. While it's possible to think of including the
shipping and returns policy, as that's a key differentiator in many businesses,
we're going to skip that for now as it complicated the model at this point
in time.

For each step in the _Sales Pipeline_ we have to define several things
about the step, but ultimately, the workflow of the _Sales Pipeline_ is
that a deal either passes to the next stage, or it doesn't. And if it
doesn't, then it's a dead deal. No sale. If it does, then the process
repeats for that next _step transition_, and we continue until all the
steps are successfully completed.

The definition of the _Sales Pipeline_ is a JSON document:
```json
[{ "name": "First Contact",
   "success": { "distribution": "pass-fail",
                "pass": 0.75 },
   "duration": { "distribution": "normal",
                 "mean": 4,
                 "stdev": 2 } },
 { "name": "Reach Decision Maker",
   "success": { "distribution": "pass-fail",
                "pass": 0.50 },
   "duration": { "distribution": "normal",
                 "mean": 5,
                 "stdev": 2 } },
 { "name": "Send Contract",
   "success": { "distribution": "pass-fail",
                "pass": 0.40 },
   "duration": { "distribution": "normal",
                 "mean": 7,
                 "stdev": 2 } },
 { "name": "Contract Signed",
   "success": { "distribution": "pass-fail",
                "pass": 0.40 },
   "duration": { "distribution": "normal",
                 "mean": 15,
                 "stdev": 5 } }]
```
where the pipeline engine converts this into something that's used in the
simulation.

### The Sales Rep

The Sales Rep will initially be a non-disturbance, but this is _clearly_ not
a valid assumption, as all the pipeline stages are really effected by the
individual moving the deal through the sales pipeline.

### The Company

Again, the company is a non-effective actor at this point, and while this is
fine for a first-order approximation, it's clearly not accurate for repeat
business or even first contact, as some companies are harder to deal with
than others.

### The Deal

The deal is the result of the sales pipeline, and the sales rep and the
company all into one outcome. This will include the time required to work
on the deal, it's final disposition, and the value of the deal. It will
be represented by the clojure data structure:
```clojure
{ :sales-rep "Amy Irving"
  :company "AAA Travel"
  :value 502.34
  :pipeline [{ :name "First Contact"
               :disposition :pass
               :duration 3.2 }
             { :name "Reach Decision Maker"
               :disposition :pass
               :duration 4.2 }
             { :name "Send Contract"
               :disposition :pass
               :duration 2.2 }
             { :name "Contract Signed"
               :disposition :pass
               :duration 6.2 }]
  :duration 15.8
  :disposition :pass }
```
where this was a successful deal. For one that only made it through _some_
of the steps of the sales pipeline:
```clojure
{ :sales-rep "Donald Duck"
  :company "Jack Daniels"
  :value 5022.34
  :pipeline [{ :name "First Contact"
               :disposition :pass
               :duration 3.2 }
             { :name "Reach Decision Maker"
               :disposition :fail }]
  :duration 3.2
  :disposition :fail }
```
thus, you can see = even on the failed deals - where they failed, and the time
it took to resolve them to that state.

## The Tenant Configuration

At this point, we have everything we need to start generating Deals. We simply
need to put it all together in a single JSON file that controls all the actors:
```json
{
  "name": "ABC Blocks",
  "sales_rep_count": 10,
  "deal_value": { "distribution": "log-normal",
                  "mean": 5,
                  "stdev": 1 },
  "deals_per_day": { "distribution": "uniform",
                     "min": 2,
                     "max": 5 },
  "pipeline": [{ "name": "First Contact",
                 "success": { "distribution": "pass-fail",
                              "pass": 0.75 },
                 "duration": { "distribution": "normal",
                               "mean": 4,
                               "stdev": 2 } },
               { "name": "Reach Decision Maker",
                 "success": { "distribution": "pass-fail",
                              "pass": 0.50 },
                 "duration": { "distribution": "normal",
                               "mean": 5,
                               "stdev": 2 } },
               { "name": "Send Contract",
                 "success": { "distribution": "pass-fail",
                              "pass": 0.40 },
                 "duration": { "distribution": "normal",
                               "mean": 7,
                               "stdev": 2 } },
               { "name": "Contract Signed",
                 "success": { "distribution": "pass-fail",
                              "pass": 0.40 },
                 "duration": { "distribution": "normal",
                               "mean": 15,
                               "stdev": 5 } }]
}
```

## Gorilla REPL

This project uses [Gorilla REPL](http://gorilla-repl.org/index.html) to help
display things from this project. It's very useful in what it does. The way
to get it started is to first, make sure all the dependencies are loaded:
```bash
$ lein deps
```
and then run the [Gorilla REPL](http://gorilla-repl.org/index.html):
```bash
$ lein gorilla
Gorilla-REPL: 0.3.4
Started nREPL server on port 54782
Running at http://127.0.0.1:54784/worksheet.html .
Ctrl+C to exit.
```
Note the URL in the log message! This is where you point your browser.

![Using the correct G-REPL URL](https://bitbucket.org/applieddl/pocket/raw/master/doc/img/grepl-url.png)

<center>
  <img src="doc/img/grepl-url.png" width="421" height="71" border="0" style="border-color=#ffffff;"
</center>

At this point, look to the three horizontal bars in the upper-right-hand corner.

![Locating the G-REPL Menu](https://bitbucket.org/applieddl/pocket/raw/master/doc/img/grepl-intro.png)

Click on them for actions, and then select "Load a Workspace" and then type in:
```
doc/uniform.clj
```
and hit return. This will show you the uniform distribution under test.

![The G-REPL page for Uniform](https://bitbucket.org/applieddl/pocket/raw/master/doc/img/grepl-uniform.png)
