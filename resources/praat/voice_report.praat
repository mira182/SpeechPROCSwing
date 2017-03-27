# Initial variables, just to make the parameters clearer
pitch_min = 60
pitch_max = 600
time_step = 0.3
silence_threshold = -25
min_pause = 0.1
min_voiced = 0.1
tier = 1

# Detect silences
sound = selected("Sound")
textgrid = To TextGrid (silences): pitch_min, time_step, 
  ... silence_threshold, min_pause, min_voiced, "silent", "sounding"
# The TextGrid is automatically selected
total_intervals = Get number of intervals: tier

# Make the remaining objects
selectObject: sound
pitch = To Pitch: 0, pitch_min, pitch_max
selectObject: sound
pulses = To PointProcess (periodic, cc): pitch_min, pitch_max

# Find beginning and end of sounding intervals 
for i to total_intervals
  selectObject: textgrid
  label$ = Get label of interval: tier, i
  if label$ == "sounding"
    start = Get start point: tier, i
    end   = Get end point: tier, i
    selectObject: sound, pitch, pulses
    report$ = Voice report: start, end,
      ... pitch_min, pitch_max, 1.3, 1.6, 0.03, 0.45
    # Do whatever you want with the report
    appendInfo: report$
  endif
endfor

# Clean up
#removeObject: textgrid, pitch, pulses
