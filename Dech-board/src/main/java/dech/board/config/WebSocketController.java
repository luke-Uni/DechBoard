// package dech.board.config;

// import java.time.LocalDateTime;
// import java.util.concurrent.ExecutorService;
// import java.util.concurrent.Executors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.messaging.handler.annotation.MessageMapping;
// import org.springframework.messaging.handler.annotation.SendTo;
// import org.springframework.messaging.simp.SimpMessagingTemplate;
// import org.springframework.stereotype.Controller;

// import io.netty.util.concurrent.Future;

// @Controller
// public class WebSocketController {
 
//     @Autowired 
//     SimpMessagingTemplate simpMessagingTemplate;
//     String destination = "/topic/messages";
 
//     ExecutorService executorService =
//             Executors.newFixedThreadPool(1);
//     Future<?> submittedTask;
 
//     @MessageMapping("/start")
//     public void startTask(){
//         if ( submittedTask != null ){
//             simpMessagingTemplate.convertAndSend(destination,
//                     "Task already started");
//             return;
//         }
//         simpMessagingTemplate.convertAndSend(destination,
//                 "Started task");
//         submittedTask = (Future<?>) executorService.submit(() -> {
//             while(true){
//                 simpMessagingTemplate.convertAndSend(destination,
//                         LocalDateTime.now().toString()
//                                 +": doing some work");
//                 Thread.sleep(10000);
//             }
//         });
//     }
 
//     @MessageMapping("/stop")
//     @SendTo("/topic/messages")
//     public String stopTask(){
//         if ( submittedTask == null ){
//             return "Task not running";
//         }
//         try {
//             submittedTask.cancel(true);
//         }catch (Exception ex){
//             ex.printStackTrace();
//             return "Error occurred while stopping task due to: "
//                     + ex.getMessage();
//         }
//         return "Stopped task";
//     }
// }