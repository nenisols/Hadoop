package hdp.websocket.test;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ApiController {

	@MessageMapping("/api")
    @SendTo("/topic/apiresponse")
	public ApiResponse apiRequest(ApiMsg msg) throws Exception {
		Thread.sleep(3000);
		
		return new ApiResponse("Hello" + msg.getName() + "!");
	}
}
