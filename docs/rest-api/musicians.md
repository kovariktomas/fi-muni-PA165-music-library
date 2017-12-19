# Musicians

## Find all musicians

```
curl -i -X GET http://localhost:8080/pa165/rest/musicians
```

## Find musician by ID

```
curl -i -X GET http://localhost:8080/pa165/rest/musicians/1
```

## Find musicians by name

```
curl -i -X GET http://localhost:8080/pa165/rest/musicians/by_name?name=Linkin+Park
```

### Create musician

```
curl -i -X POST \
	--header "Content-Type: application/json" \
	--data '{"name":"Linkin Park"}' \
	http://admin%40example.com:password@localhost:8080/pa165/rest/musicians/create
```

### Update musician

```
curl -i -X PUT \
	--header "Content-Type: application/json" \
	--data '{"name":"Fort Minor"}' \
	http://admin%40example.com:password@localhost:8080/pa165/rest/musicians/1
```

### Delete musician

```
curl -i -X DELETE http://admin%40example.com:password@localhost:8080/pa165/rest/musicians/1
```
