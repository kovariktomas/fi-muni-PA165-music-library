# Songs

## Find all songs

```
curl -i -X GET http://localhost:8080/pa165/rest/songs
```

## Find song by ID

```
curl -i -X GET http://localhost:8080/pa165/rest/songs/1
```

### Create song

```
curl -i -X POST \
	--header "Content-Type: application/json" \
	--data '{"musicianId":1,"albumId":1,"genreId":1,"title":"Papercut","bitrate":320,"position":0,"commentary":""}' \
	http://localhost:8080/pa165/rest/songs/create
```

### Update song

```
curl -i -X PUT \
	--header "Content-Type: application/json" \
	--data '{"title":"In The End"}' \
	http://localhost:8080/pa165/rest/songs/1
```

### Delete song

```
curl -i -X DELETE http://localhost:8080/pa165/rest/songs/1
```
