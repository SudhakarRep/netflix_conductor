package com.go.workflow.demoworkflow;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;
import com.netflix.conductor.common.metadata.tasks.TaskResult.Status;

/**
 * @author Sudhakar
 * 
 */
public class SampleWorker implements Worker {

	private String taskDefName;
	
	public SampleWorker(String taskDefName) {
		this.taskDefName = taskDefName;
	}
	
	public String getTaskDefName() { 
		return taskDefName;
	}

	public TaskResult execute(Task task) {
		System.out.printf("Executing %s%n", taskDefName);
		
		TaskResult result = new TaskResult(task);
		result.setStatus(Status.COMPLETED);
		
		//Register the output of the task
		result.getOutputData().put("outputKey1", "value");
		result.getOutputData().put("oddEven", 1);
		result.getOutputData().put("mod", 4);
		
		return result;
	}

}