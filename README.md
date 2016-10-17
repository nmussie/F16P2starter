# F16P2starter
This program is a memory management package for storing variable-length records with a large memory space. 
The records stored are artist and song names from a Million Song database. 
The program implements a HashTable for the storing of the "handles" that points to the position 
where the artist/song is held in the memory manager. The program also includes a range query feature,
which uses a 2-3 B+ tree in order to retrieve all songs by a certain artist as well as, all artists
who've written the same song. 
