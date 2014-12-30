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

![Locating the G-REPL Menu](raw/master/doc/img/grepl-intro.png)

Click on them for actions, and then select "Load a Workspace" and then type in:
```
doc/uniform.clj
```
and load. This will show you the uniform distribution under test.

![The G-REPL page for Uniform](raw/master/doc/img/grepl-uniform.png)
