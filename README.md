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

At this point, look to the three horizontal bars in the upper-right-hand corner.

![Locating the G-REPL Menu](https://bitbucket.org/applieddl/pocket/raw/master/doc/img/grepl-intro.png)

Click on them for actions, and then select "Load a Workspace" and then type in:
```
doc/uniform.clj
```
and hit return. This will show you the uniform distribution under test.

![The G-REPL page for Uniform](https://bitbucket.org/applieddl/pocket/raw/master/doc/img/grepl-uniform.png)
