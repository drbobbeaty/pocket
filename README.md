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