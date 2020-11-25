##LINK TO ORIGINAL DATASET
#https://data.cityofnewyork.us/Social-Services/NYC-311-Data/jrb2-thup

import requests
import json

response = requests.get(url='https://data.cityofnewyork.us/resource/jrb2-thup.json', params={'$limit':200000})
print(type(response.text))

resp_object = json.loads(response.text)

print(len(resp_object))


#create csv file from response data
with open("311data.csv", "w") as f:
    print('Creating CSV file ... ')
    #count=0
    f.write("Incident Zip,Created Date,Closed Date,Complaint Type,Borough\n")
    for i in range(len(resp_object)):
        record = ''
        if ("incident_zip" not in resp_object[i]):
            record += " "

        else:
            record += resp_object[i]['incident_zip']

        record += ','
        
        if ("created_date" not in resp_object[i]):
            record += ' '

        else: 
            record += resp_object[i]['created_date']

        record += ','

        if ("closed_date" not in resp_object[i]):
            record += ' '
        
        else:
            record += resp_object[i]['closed_date']

        record += ','

        if ('complaint_type' not in resp_object[i]):
            record += ' '
        
        else: 
            record += resp_object[i]['complaint_type']

        record += ','

        if ('borough' not in resp_object[i]):
            record += ' '

        else: 
            record += resp_object[i]['borough']
        
        if(i != len(resp_object)-1):
            record += '\n'
        
        f.write(record)
print('Done')
        


#print(count)


        
