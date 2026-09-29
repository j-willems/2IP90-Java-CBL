# Music player with playlist support.

Advanced topics: file handling, data structures.


| # | Name | How to | Notes |
| --- | --- | --- | --- |
| 1 | Display Interface | Launch the app and display the main library tab. | The initial library is empty since a folder has not yet been selected. |
| 2 | Open library | Add a button to select a folder containing music to be used as the song library. | The app should support the most common music file types e.g. mp3, wav, flac, ... |
| 3 | Media controls | Add play/pause/previous/next/loop buttons and a progress bar. | These controls should work in both the main and playlist tab. |
| 4 | Add to playlist | Add two buttons to add the selected song(s) to the current playlist. | Song (s)should be added either at the beginning or at the end of the current playlist, depending on the button used. |
| 5 | Playlist tab | Add a button to change to the playlist tab to display songs in the current playlist. | Use a circular doubly linked list to represent the playlist to make inserting or removing items and looping efficient. |
| 6 | Edit playlist | Add buttons to reorder or remove songs (individually or all at once) from the current playlist.  | The total paly duration of the playlist should be updated. |
| 7 | Sort Playlist | Add button to sort playlist. | Songs can be sorted by metadata (Artist, duration, album, etc.). |
| 8 | Save Playlist | Add button to save playlist as a file locally. | The save file should preserve the order of the playlist. |
| 9 | Load playlist | Add a button to load a playlist from a previously saved file. | The saved order should be loaded. |
