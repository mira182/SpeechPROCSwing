form arguments
 real timeStep 0.05
 word File
 word outputFileName
endform

Erase all

Read from file... 'file$'
object_name$ = selected$("Sound")
select Sound 'object_name$'

To Pitch: 0.0, 75.0, 600.0

startTime = Get start time
endTime = Get end time
numberOfTimeSteps = (endTime - startTime) / timeStep
appendFileLine: outputFileName$, "tmin", " ", "tmax", " ", "mean", " ", "fmin", " ", "fmax", " ", "stdev", " ", "fvr",  " ", "relfstdevper",  " ", "relfvrper"
for step to numberOfTimeSteps
    tmin = startTime + (step - 1) * timeStep
    tmax = tmin + timeStep
    mean = Get mean: tmin, tmax, "Hertz"
    minimum = Get minimum: tmin, tmax, "Hertz", "Parabolic"
    maximum = Get maximum: tmin, tmax, "Hertz", "Parabolic"
    stdev = Get standard deviation: tmin, tmax, "Hertz"
    fvr = maximum - minimum
    relfvrper = (fvr / mean) * 100
    relfstdevper = (stdev / mean) * 100
    appendFileLine: outputFileName$, fixed$ (tmin, 6), " ", fixed$ (tmax, 6), " ", fixed$ (mean, 2),
    ... " ", fixed$ (minimum, 2), " ", fixed$ (maximum, 2), " ", fixed$ (stdev, 2), " ", fixed$ (fvr, 4), " ", fixed$ (relfstdevper, 4), " ", fixed$ (relfvrper, 4)
endfor