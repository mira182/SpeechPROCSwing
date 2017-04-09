form arguments
 real timeStep 0.05
 real pitch_min 75.0
 real pitch_max 600.0
 word File
 word outputFileName
 word outputStatsFileName
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
    relfvrper = (fvr / mean)
    relfstdevper = (stdev / mean)
    appendFileLine: outputFileName$, fixed$ (tmin, 6), " ", fixed$ (tmax, 6), " ", fixed$ (mean, 2),
    ... " ", fixed$ (minimum, 2), " ", fixed$ (maximum, 2), " ", fixed$ (stdev, 2), " ", fixed$ (fvr, 4), " ", percent$ (relfstdevper, 4), " ", percent$ (relfvrper, 4)
endfor

pointProcess = To PointProcess

selectObject: sound, pitch, pointProcess


voiceReport$ = Voice report: 0, 0, pitch_min, pitch_max, 1.3, 1.6, 0.03, 0.45
jitterLoc = extractNumber (voiceReport$, "Jitter (local): ")
jitterLocAbs = extractNumber (voiceReport$, "Jitter (local, absolute): ")
jitterRap = extractNumber (voiceReport$, "Jitter (rap): ")
jitterPpq = extractNumber (voiceReport$, "Jitter (ppq5): ")
jitterDdp = extractNumber (voiceReport$, "Jitter (ddp): ")

shimmerLoc = extractNumber (voiceReport$, "Shimmer (local): ")
shimmerLocDb = extractNumber (voiceReport$, "Shimmer (local, dB): ")
shimmerApq3 = extractNumber (voiceReport$, "Shimmer (apq3): ")
shimmerApq5 = extractNumber (voiceReport$, "Shimmer (apq5): ")
shimmerApq11 = extractNumber (voiceReport$, "Shimmer (apq11): ")
shimmerDda = extractNumber (voiceReport$, "Shimmer (dda): ")

mean = extractNumber (voiceReport$, "Mean pitch: ")
median = extractNumber (voiceReport$, "Median pitch: ")
stdev = extractNumber (voiceReport$, "Standard deviation: ")
f0min = extractNumber (voiceReport$, "Minimum pitch: ")
f0max = extractNumber (voiceReport$, "Maximum pitch: ")
f0VR = f0max - f0min
relF0VR = (f0VR/mean)
relF0Stdev = (stdev / mean)

hnrAut = extractNumber (voiceReport$, "Mean autocorrelation: ")
hnrNH = extractNumber (voiceReport$, "Mean noise-to-harmonics ratio: ")
hnrHN = extractNumber (voiceReport$, "Mean harmonics-to-noise ratio: ")

voicingFrac = extractNumber (voiceReport$, "Fraction of locally unvoiced frames: ")
voicingNBreaks = extractNumber (voiceReport$, "Number of voice breaks: ")
voicingDegree$ = extractLine$ (voiceReport$, "Degree of voice breaks: ")

appendFileLine: outputStatsFileName$, "Jitter(local) Jitter(local,abs) Jitter(rap) Jitter(ppq) Jitter(ddp) Shimmer(local) Shimmer(local,dB), Shimmer(apq3) Shimmer(apq5) Shimmer(apq11) Shimmer(dda) F0mean(Hz) F0median(Hz) F0stdev(Hz) F0min(Hz) F0max(Hz) F0VR(Hz) relF0VR(%) relF0Stdev(%) HNR(aut) HNR(nh) HNR(hn) VoicingFraction VoicingNBreaks VoicingDegree Test"
appendFileLine: outputStatsFileName$, percent$ (jitterLoc, 3), " ", fixed$ (jitterLocAbs, 3), " ", percent$ (jitterRap, 3), " ", percent$ (jitterPpq, 3), " ", percent$ (jitterDdp, 3), " ", percent$ (shimmerLoc, 3), " ", fixed$ (shimmerLocDb, 3), " ", percent$ (shimmerApq3, 3), " ", percent$ (shimmerApq5, 3), " ", percent$ (shimmerApq11, 3), " ", percent$ (shimmerDda, 3), " ", fixed$ (mean, 3), " ", fixed$ (median, 3), " ", fixed$ (stdev, 3), " ", fixed$ (f0min, 3), " ", fixed$ (f0max, 3), " ", fixed$ (f0VR, 3), " ", percent$ (relF0VR, 3), " ", percent$ (relF0Stdev, 3), " ", fixed$ (hnrAut, 3), " ", fixed$ (hnrNH, 3), " ", fixed$ (hnrHN, 3), " ", percent$ (voicingFrac, 3), " ", fixed$ (voicingNBreaks, 3), " ", voicingDegree$