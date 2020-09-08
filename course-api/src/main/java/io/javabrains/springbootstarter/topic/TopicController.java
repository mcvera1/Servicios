package io.javabrains.springbootstarter.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TopicController {
	
	@Autowired
	private TopicService topicService;

	@GetMapping("/topics") //listar
	public List<Topic> getAllTopics(){
		return topicService.getAllTopics();
	}
	
	@GetMapping("/topics/{id}") //buscar
	public Topic getTopic(@PathVariable("id") String id){ //si el parametro en el request param es diferente se pone entre parentesis despues del pathvariable
		return topicService.getTopic(id);
	}
	
	@PostMapping(value = "/topics") //insertar
	public void addTopic(@RequestBody Topic topic){
		topicService.addTopic(topic);
	}
	
	@PutMapping(value = "/topics/{id}") //actualizar
	public void updateTopic(@RequestBody Topic topic, @PathVariable("id") String id){
		topicService.updateTopic(id, topic);
	}
	
	@DeleteMapping(value = "/topics/{id}") //eliminar
	public void deleteTopic(@PathVariable("id") String id){
		topicService.deleteTopic(id);
	}
	
}
