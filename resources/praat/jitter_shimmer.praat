form arguments
 real pitch_min 75
 real pitch_max 600
 word File /home/hynstm/NetBeansProjects/SpeechPROCSwing/test/resources/Plosive1.wav
 word outputFileName /home/hynstm/test.csv
endform

Erase all

Read from file... 'file$'
sound = selected("Sound")

pitch = To Pitch: 0.0, 75.0, 600.0

pointProcess = To PointProcess

selectObject: sound, pitch, pointProcess

voiceReport$ = Voice report: 0, 0, pitch_min, pitch_max, 1.3, 1.6, 0.03, 0.45
jitter = extractNumber (voiceReport$, "Jitter (local): ")
shimmer = extractNumber (voiceReport$, "Shimmer (local): ")
writeInfoLine: "Jitter = ", percent$ (jitter, 3), ", shimmer = ", percent$ (shimmer, 3)
