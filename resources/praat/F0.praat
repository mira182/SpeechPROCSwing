form arguments
 real timeStep 0.05
 real pitch_min 75.0
 real pitch_max 600.0
 word File
 word outputFileName
 word f0StatsOutputFile
endform

Erase all

Read from file... 'file$'
sound = selected("Sound")

pitch = To Pitch: 0.0, pitch_min, pitch_max

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
    median = Get quantile: 0, 0, 0.50, "Hertz"
    fvr = maximum - minimum
    relfvrper = (fvr / mean) * 100
    relfstdevper = (stdev / mean) * 100
    appendFileLine: outputFileName$, fixed$ (tmin, 6), " ", fixed$ (tmax, 6), " ", fixed$ (mean, 2),
    ... " ", fixed$ (minimum, 2), " ", fixed$ (maximum, 2), " ", fixed$ (stdev, 2), " ", fixed$ (fvr, 4), " ", percent$ (relfstdevper, 4), " ", percent$ (relfvrper, 4)
endfor

pointProcess = To PointProcess

selectObject: sound, pitch, pointProcess

appendFileLine: f0StatsOutputFile$, "Jitter (%) Shimmer (%) F0 mean (Hz) F0 median (Hz) F0 stdev (Hz) F0 min (Hz) F0 max (Hz)"
voiceReport$ = Voice report: 0, 0, pitch_min, pitch_max, 1.3, 1.6, 0.03, 0.45
jitter = extractNumber (voiceReport$, "Jitter (local): ")
shimmer = extractNumber (voiceReport$, "Shimmer (local): ")
mean = extractNumber (voiceReport$, "Mean pitch: ")
median = extractNumber (voiceReport$, "Median pitch: ")
stdev = extractNumber (voiceReport$, "Standard deviation: ")
f0min = extractNumber (voiceReport$, "Minimum pitch: ")
f0max = extractNumber (voiceReport$, "Maximum pitch: ")
appendFileLine: f0StatsOutputFile$, percent$ (jitter, 3), " ", percent$ (shimmer, 3), " ", fixed$ (mean, 3), " ", fixed$ (median, 3), " ", fixed$ (stdev, 3), " ", fixed$ (f0min, 3), " ", fixed$ (f0max, 3)