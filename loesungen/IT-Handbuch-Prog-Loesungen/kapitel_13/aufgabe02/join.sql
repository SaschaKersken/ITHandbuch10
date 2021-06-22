SELECT interpret_name Interpret,
       album_titel Album,
       album_jahr Jahr,
       track_titel Track,
       concat(track_dauer div 60, ":", if (track_dauer % 60 < 10, concat("0", track_dauer % 60), track_dauer % 60)) Dauer
FROM interpreten
INNER JOIN alben ON interpret_id = album_interpret
INNER JOIN tracks ON album_id = track_album
