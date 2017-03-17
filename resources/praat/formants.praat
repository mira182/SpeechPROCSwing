form arguments
 real timeStep
 real maxFormantsNumber
 integer maxFormant
 real windowLength
 integer preemphasis
 word File
 word outputFileName
endform

Erase all

Read from file... 'file$'
object_name$ = selected$("Sound")
select Sound 'object_name$'

To Formant (burg)... timeStep$ maxFormantsNumber$ maxFormant$ windowLength$ preemphasis$
table = Down to Table: "no", "yes", 6, "yes", 3, "yes", 3, "yes"

select Table 'object_name$'
Save as comma-separated file: outputFileName$