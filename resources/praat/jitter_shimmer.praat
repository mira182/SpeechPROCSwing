form arguments
 real pitch_min
 real pitch_max
 word File
 word outputFileName
endform

Erase all

Read from file... 'file$'
sound = selected("Sound")

pitch = To Pitch: 0.0, 75.0, 600.0

pointProcess = To PointProcess

selectObject: sound, pitch, pointProcess

appendFileLine: outputFileName$, "Jitter (%), Shimmer (%), F0 mean (Hz), F0 median (Hz), F0 stdev (Hz), F0 min (Hz), F0 max (Hz)"
voiceReport$ = Voice report: 0, 0, 75, 500, 1.3, 1.6, 0.03, 0.45
jitter = extractNumber (voiceReport$, "Jitter (local): ")
shimmer = extractNumber (voiceReport$, "Shimmer (local): ")
mean = extractNumber (voiceReport$, "Mean pitch: ")
median = extractNumber (voiceReport$, "Median pitch: ")
stdev = extractNumber (voiceReport$, "Standard deviation: ")
f0min = extractNumber (voiceReport$, "Minimum pitch: ")
f0max = extractNumber (voiceReport$, "Maximum pitch: ")
appendFileLine: outputFileName$, percent$ (jitter, 3), ",", percent$ (shimmer, 3), ",", fixed$ (mean, 3), ",", fixed$ (median, 3), ",", fixed$ (stdev, 3), ",", fixed$ (f0min, 3), ",", fixed$ (f0max, 3)
