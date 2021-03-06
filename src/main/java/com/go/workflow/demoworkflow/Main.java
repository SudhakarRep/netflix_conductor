package com.go.workflow.demoworkflow;

import com.netflix.conductor.client.http.TaskClient;
import com.netflix.conductor.client.task.WorkflowTaskCoordinator;
import com.netflix.conductor.client.worker.Worker;

/**
 * @author Sudhakar
 *
 */
public class Main {

	public static void main(String[] args) {
		
		TaskClient taskClient = new TaskClient();
		taskClient.setRootURI("http://localhost:8080/api/");		//Point this to the server API
		
		int threadCount = 2;			//number of threads used to execute workers.  To avoid starvation, should be same or more than number of workers
		
		Worker worker1 = new SampleWorker("task_1");
		Worker worker2 = new SampleWorker("task_2");
		Worker worker3 = new SampleWorker("task_3");
		
		//Create WorkflowTaskCoordinator
		WorkflowTaskCoordinator.Builder builder = new WorkflowTaskCoordinator.Builder();
		WorkflowTaskCoordinator coordinator = builder.withWorkers(worker1, worker2, worker3).withThreadCount(threadCount).withTaskClient(taskClient).build();
		
		//Start for polling and execution of the tasks
		coordinator.init();

	}
}