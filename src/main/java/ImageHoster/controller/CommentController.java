package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    // Add user and image to the comment and save it.
    // Redirect to the image page after saving the comment.
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(@PathVariable("imageId") int imageId,
                                @PathVariable("imageTitle") String imageTitle,
                                @RequestParam String comment, HttpSession session) {
        Image image = imageService.getImage(imageId);
        User user = (User) session.getAttribute("loggeduser");

        Comment newComment = new Comment();
        newComment.setImage(image);
        newComment.setUser(user);
        newComment.setText(comment);
        newComment.setCreatedDate(LocalDate.now());

        commentService.uploadComment(newComment);

        return "redirect:/images/" + imageId + "/" + imageTitle;
    }
}
