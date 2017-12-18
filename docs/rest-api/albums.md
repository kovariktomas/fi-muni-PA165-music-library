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
	--data '{"name":"Hybrid Theory"}' \
	http://localhost:8080/pa165/rest/albums/create
```

### Update album

```
curl -i -X PUT \
	--header "Content-Type: application/json" \
	--data '{"name":"Meteora"}' \
	http://localhost:8080/pa165/rest/albums/1
```

### Delete album

```
curl -i -X DELETE http://localhost:8080/pa165/rest/albums/1
```
