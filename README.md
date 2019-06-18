# netflix_conductor

1.	Run the main from the APP
2.	Swagger – 
      1.	post /metadata/taskdefs 
           
           [{
                  "name": "task_1",
                  "taskReferenceName": "task_1",
                  "description":"Assign score for the conversation",
                  "inputParameters": {
                    "eventData": "${workflow.input.eventData}"
                  },
                  "type": "SIMPLE",
                  "startDelay": 0,
                  "optional": false
                }]

      2. post /metadata/workflow       
      
            {
              "name": "task_1",
              "description": "MIM workflow 1",
              "version": 28,
              "tasks": [
                {
                  "name": "task_1",
                  "taskReferenceName": "task_1",
                  "description": "TEST 1",
                  "inputParameters": {
                    "eventData": "${workflow.input.eventData}"
                  },
                  "type": "SIMPLE",
                  "startDelay": 0,
                  "optional": false,
                  "taskDefinition": {
                    "name": "task_55_2",
                    "description": "TEST 1",
                    "retryCount": 3,
                    "timeoutSeconds": 0,
                    "timeoutPolicy": "TIME_OUT_WF",
                    "retryLogic": "FIXED",
                    "retryDelaySeconds": 60,
                    "responseTimeoutSeconds": 3600,
                    "rateLimitPerFrequency": 0,
                    "rateLimitFrequencyInSeconds": 1
                  }
                }

              ],
              "schemaVersion": 2,
              "restartable": true
            }

3. Advance Rest Client
      POST (Make sure about the version)
      
      http://localhost:8080/api/workflow
      {
       "name":"task_55_2_TEST_WORKFLOW_1",
        "version":28
      }

Now check your method will be executed

