package com.dovgopolyy.jett.server.search;

import com.dovgopolyy.jett.server.search.exception.DocumentNotFound;
import com.dovgopolyy.jett.server.search.exception.DocumentsNotFound;
import com.dovgopolyy.jett.server.search.exception.NullNotAllowedException;
import com.dovgopolyy.jett.server.search.service.SearchService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.HttpStatus.*;

@Api("Search Engine")
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService service;

    @ApiOperation("Add document into the search engine")
    @ApiResponses({@ApiResponse(code = 201, message = "Document created")})
    @ResponseStatus(CREATED)
    @PostMapping(path = "/document",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long addDocument(
            @ApiParam("Document with tokens separated by space")
            @RequestBody String document) {
        checkForNull(document);
        return service.addDocument(document);
    }

    @ApiOperation("Get document by id")
    @GetMapping(path = "/document/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDocument(
            @ApiParam("Document's id")
            @PathVariable("id") Long id) {
        checkForNull(id);
        return service.getDocument(id).orElseThrow(DocumentNotFound::new);
    }

    @ApiOperation("Search on a string of tokens (words) to return keys of all documents that contain all tokens in the set")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Long> search(
            @ApiParam("Tokens separated by space")
            @RequestParam String tokens) {
        checkForNull(tokens);
        return service.search(tokens).orElseThrow(DocumentsNotFound::new);
    }

    private void checkForNull(Object obj) {
        if(obj == null)
            throw new NullNotAllowedException();
    }


}
