#!/bin/bash

# launch script using ./src/test/resources/trafic-simulation.sh

SLEEP_DURATION=1
 
echo $SLEEP_DURATION

for i in {0..100}
do
curl http://localhost:10080/petstore-rest-mvc/catalog/category/1
sleep $SLEEP_DURATION
curl http://localhost:10080/petstore-rest-mvc/catalog/category/2
sleep $SLEEP_DURATION
curl http://localhost:10080/petstore-rest-mvc/catalog/category/3
sleep $SLEEP_DURATION
curl http://localhost:10080/petstore-rest-mvc/catalog/category/4
sleep $SLEEP_DURATION
curl http://localhost:10080/petstore-rest-mvc/catalog/category/5
done



# sleep $SLEEP_DURATION
# curl http://localhost:10080/petstore-rest-mvc/catalog/product/1
# sleep $SLEEP_DURATION
# curl http://localhost:10080/petstore-rest-mvc/catalog/product/3
# sleep $SLEEP_DURATION
# curl http://localhost:10080/petstore-rest-mvc/catalog/product/5
# sleep $SLEEP_DURATION
# curl http://localhost:10080/petstore-rest-mvc/catalog/item/1
# sleep $SLEEP_DURATION
# curl http://localhost:10080/petstore-rest-mvc/catalog/item/10
# sleep $SLEEP_DURATION
# curl http://localhost:10080/petstore-rest-mvc/catalog/item/20
# sleep $SLEEP_DURATION
# curl http://localhost:10080/petstore-rest-mvc/catalog/products/1
# sleep $SLEEP_DURATION
# curl http://localhost:10080/petstore-rest-mvc/catalog/items/6
# sleep $SLEEP_DURATION
# curl http://localhost:10080/petstore-rest-mvc/catalog/categories