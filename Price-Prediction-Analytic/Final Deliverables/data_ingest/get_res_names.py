##LINK TO ORIGINAL DATASET
#https://data.cityofnewyork.us/Social-Services/NYC-311-Data/jrb2-thup

import requests
import json

response = requests.get(url='https://data.cityofnewyork.us/resource/43nn-pn8j.json', params={'$limit':200000})
print(type(response.text))

resp_object = json.loads(response.text)

print(len(resp_object))


#create csv file from response data
with open("rest_name.csv", "w") as f:
    print('Creating CSV file ... ')
        #count=0
    for i in range(len(resp_object)):
        record = ''
        if ("camis" not in resp_object[i]):
            record += " "

        else:
            record += resp_object[i]['camis']

        record += ','

        if ("dba" not in resp_object[i]):
            record += " "

        else:
            record += resp_object[i]['dba']

        record += ','

        if ("boro" not in resp_object[i]):
            record += " "

        else:
            record += resp_object[i]['boro']

        record += ','

        if ("zipcode" not in resp_object[i]):
            record += " "

        else:
            record += resp_object[i]['zipcode']

        if(i != len(resp_object)-1):
            record += '\n'

        f.write(record)



#print(count)


        
