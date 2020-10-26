package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    //The method calls the createImage() method in the Repository and passes the image to be persisted in the database
    public void uploadComment(Comment comment) {
        commentRepository.uploadComment(comment);
    }

}
