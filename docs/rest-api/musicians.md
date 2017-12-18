# Musicians

## Find all musicians

```
curl -i -X GET http://localhost:8080/pa165/rest/musicians
```

## Find musician by ID

```
curl -i -X GET http://localhost:8080/pa165/rest/musicians/1
```

### Create musician

```
curl -i -X POST \
	--header "Content-Type: application/json" \
	--data '{"name":"Linkin Park"}' \
	http://localhost:8080/pa165/rest/musicians/create
```

### Update musician

```
curl -i -X PUT \
	--header "Content-Type: application/json" \
	--data '{"name":"Fort Minor"}' \
	http://localhost:8080/pa165/rest/musicians/1
```

### Delete musician

```
curl -i -X DELETE http://localhost:8080/pa165/rest/musicians/1
```
