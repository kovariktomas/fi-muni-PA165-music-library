# Genres

## Find all genres

```
curl -i -X GET http://localhost:8080/pa165/rest/genres
```

## Find genre by ID

```
curl -i -X GET http://localhost:8080/pa165/rest/genres/1
```

## Find genres by name

```
curl -i -X GET http://localhost:8080/pa165/rest/genres/by_name?name=Rock
```

### Create genre

```
curl -i -X POST \
	--header "Content-Type: application/json" \
	--data '{"name":"Jazz"}' \
	http://localhost:8080/pa165/rest/genres/create
```

### Update genre

```
curl -i -X PUT \
	--header "Content-Type: application/json" \
	--data '{"name":"Rock"}' \
	http://localhost:8080/pa165/rest/genres/1
```

### Delete genre

```
curl -i -X DELETE http://localhost:8080/pa165/rest/genres/1
```
