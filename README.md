# CartesianProduct

Bash Cartesian Product 

Braces tell the Bash shell to generate what's called the "Cartesian 
product" of the given set, where elements of the set are comma-separated. 
The problem at hand is to implement this feature of Bash, i.e. produce the 
same output as Bash does for a given input string. 

For example: 
$ echo a{b,c}d{e,f,g}hi 
abdehi abdfhi abdghi acdehi acdfhi acdghi 
$ echo a{b,c{d,e,f}g,h}ij{k,l} 
abijk abijl acdgijk acdgijl acegijk acegijl acfgijk acfgijl ahijk ahijl 
