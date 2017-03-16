form arguments
 word File
 word outputFileName
endform

Erase all

Read from file... 'file$'
object_name$ = selected$("Sound")
select Sound 'object_name$'

To Formant (burg)... 0.005 5 5000 0.025 50
table = Down to Table: "no", "yes", 6, "yes", 3, "yes", 3, "yes"

select Table 'object_name$'
Save as comma-separated file: outputFileName$