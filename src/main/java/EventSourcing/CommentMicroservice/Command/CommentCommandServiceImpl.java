package EventSourcing.CommentMicroservice.Command;

import EventSourcing.API.Subject;
import EventSourcing.API.SubscriberEventStore;
import EventSourcing.BasicClasses.CommentCreateDTO;
import EventSourcing.BasicClasses.CreateCommentCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/*Implementation of interface "CommentCommandService"*/
/*A state change within an application starts with a command*/
@Service
public class CommentCommandServiceImpl implements CommentCommandService, Subject {

    /*Convenience interface provided by Axon used to dispatch commands*/
    private final CommandGateway commandGateway;
    private SubscriberEventStore observer;

    //This constructor is called immediately when the application is run
    public CommentCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
        System.out.println("Inside Constructor CommentCommandServiceImpl(CommandGateway commandGateway");
        observer = new SubscriberEventStore();
    }

    /*send() method here will send a command and wait for a response*/
/*    @Override
    public CompletableFuture<String> createComment(Comment comment) {
        System.out.println("Inside createComment(CommentCreateDTO commentCreateDTO)");
        CreateCommentCommand newComment = new CreateCommentCommand(UUID.randomUUID(), UUID.fromString("82525212-6f16-11ea-bc55-0242ac130003"), comment.getMessage());
        notifyObserver(newComment);
        return commandGateway.send(newComment);
    }*/

    /*send() method here will send a command and wait for a response*/
    @Override
    public CompletableFuture<String> createComment(CommentCreateDTO commentCreateDTO) {
        System.out.println("Inside createComment(CommentCreateDTO commentCreateDTO)");
        /*TODO create local UUID variable*/
        CreateCommentCommand newComment = new CreateCommentCommand(UUID.randomUUID(), UUID.fromString("82525212-6f16-11ea-bc55-0242ac130003"), commentCreateDTO.getMessage());
        notifyObserver(newComment);
        return commandGateway.send(newComment);
    }

    @Override
    public void notifyObserver(CreateCommentCommand newComment)
    {
        observer.updateCreationEvent(newComment);
    }

    /*TODO: Take care of passing the parentID in the above code*/
}

