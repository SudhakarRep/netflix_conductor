package com.go.workflow.demoworkflow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import com.netflix.conductor.common.metadata.tasks.TaskResult.Status;

/**
 * @author Sudhakar
 * 
 */
public class MetricProcessorWorker implements Worker {

	private String taskDefName;
	
	public MetricProcessorWorker(String taskDefName) {
		this.taskDefName = taskDefName;
	}
	
	public String getTaskDefName() { 
		return taskDefName;
	}

	public TaskResult execute(Task task) {
		System.out.printf("Metric Processor....");
		String output = "";
		
		try {
			String opsRampURL = "https://api.vistara.io/api/v2/metric/search?tenant=client_570833&rtype=DEVICE&resource=081253ea-bee9-4a88-b32f-cfd652321553&metric=system.cpu.utilization&startTime=1560839558&endTime=1560843088";
			URL url = new URL(opsRampURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", "Bearer 43e7c0b6-0fb3-4489-8d69-18908b7947cd");
			conn.setRequestProperty("Accept", "application/json");
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
		    
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));	
			while ((output = br.readLine()) != null) {
				System.out.println("Output:"+output);
			}
			
			if (conn != null) conn.disconnect();	
		
		} catch (Exception ex) {
			System.out.printf("ERROR: %s", ex);
		}
		
		TaskResult result = new TaskResult(task);
		result.setStatus(Status.COMPLETED);
		result.getOutputData().put("result", output);
		return result;
		
	}

}