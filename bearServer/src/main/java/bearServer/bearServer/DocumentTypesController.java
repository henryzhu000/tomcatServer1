package bearServer.bearServer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DocumentTypesController {

    public record DocumentType(String displayOrder, String documentCategoryId) {}

    @GetMapping("/documentTypes")
    public List<DocumentType> all() {
        return List.of(
                new DocumentType("temp", null),
                new DocumentType(null, "1")
        );
    }
}
