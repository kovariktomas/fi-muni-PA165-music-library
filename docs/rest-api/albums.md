# Albums

## Find all albums

```
curl -i -X GET http://localhost:8080/pa165/rest/albums
```

## Find album by ID

```
curl -i -X GET http://localhost:8080/pa165/rest/albums/1
```

### Create album

```
curl -i -X POST \
	--header "Content-Type: application/json" \
	--data '{"releaseDate":"2000-10-24","title":"Hybrid Theory","commentary":"","albumArt":null}' \
	http://localhost:8080/pa165/rest/albums/create
```

### Update album

```
curl -i -X PUT \
	--header "Content-Type: application/json" \
	--data '{"releaseDate":"2003-03-25","title":"Meteora"}' \
	http://localhost:8080/pa165/rest/albums/1
```

### Delete album

```
curl -i -X DELETE http://localhost:8080/pa165/rest/albums/1
```
