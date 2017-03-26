form arguments
 real timeStep 0.005
 real maxFormantsNumber 3.0
 real maxFormant 5000.0
 real windowLength 0.025
 real preemphasis 50.0
 word File
 word outputFileName
endform

Erase all

Read from file... 'file$'
object_name$ = selected$("Sound")
select Sound 'object_name$'

To Formant (burg)... timeStep maxFormantsNumber maxFormant windowLength preemphasis
table = Down to Table: "no", "yes", 6, "yes", 3, "yes", 3, "yes"

select Table 'object_name$'
Save as comma-separated file: outputFileName$