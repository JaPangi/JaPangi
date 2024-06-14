import pymysql
import random

db = pymysql.connect(
    host="peterpan-database.cfc8euiw8o1n.ap-northeast-2.rds.amazonaws.com",
    port=3306,
    user="peterpan",
    passwd="taewan1256!",
    db='peterpan_rds'
)

cursor = db.cursor()

for i in range(100):
    query = "INSERT INTO orders (ordered_at, drink_id, vending_machine_id) VALUES ('2024-{}-{}T04:07:48.403244', {}, {});".format(
        random.randrange(2, 3),
        random.randrange(1, 30),
        random.randrange(1, 7),
        random.randrange(4, 8),
    )
    cursor.execute(query)

db.commit()
db.close()