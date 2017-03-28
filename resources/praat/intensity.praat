form arguments
 word File
 word outputFileName
 word outputStatsFileName
endform

Erase all

Read from file... 'file$'
sound = selected ("Sound")

tmin = Get start time
tmax = Get end time

To Intensity: 75, 0.001

appendFileLine: outputFileName$, "Time (s)", " ", "Intensity (dB)"
for i to (tmax-tmin)/0.01
    time = tmin + i * 0.01
    intensity = Get value at time: time, "Cubic"
    appendFileLine: outputFileName$, fixed$ (time, 2), " ", fixed$ (intensity, 3)
endfor

min = Get minimum... 0.0 0.0 Parabolic
timeOfMin = Get time of minimum... 0.0 0.0 Parabolic
max = Get maximum... 0.0 0.0 Parabolic
timeOfMax = Get time of maximum... 0.0 0.0 Parabolic
mean = Get mean... 0.0 0.0 Energy
stdev = Get standard deviation... 0.0 0.0
median = Get quantile... 0.0 0.0 0.5

appendFileLine: outputStatsFileName$, "Min (dB)", " ", "Time of min (s)", " ", "Max (dB)", " ", "Time of max (s)", " ", "Mean (dB)", "Median (dB)", " ", "Stdev (dB)"
appendFileLine: outputStatsFileName$, min, " ", timeOfMin, " ", max, " ", timeOfMax, " ", mean, " ", median, " ", stdev
