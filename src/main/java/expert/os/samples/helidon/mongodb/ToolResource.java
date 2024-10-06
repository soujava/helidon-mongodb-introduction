package expert.os.samples.helidon.mongodb;

import jakarta.data.Direction;
import jakarta.data.Order;
import jakarta.data.Sort;
import jakarta.data.page.PageRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.logging.Logger;

@Path("/tools")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ToolResource {

    private static final Logger LOGGER = Logger.getLogger(ToolResource.class.getName());

    private final ToolRepository toolRepository;

    @Inject
    public ToolResource(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }


    @GET
    public List<Tool> findAllTools(@QueryParam("page") @DefaultValue("1") long page,
                                   @QueryParam("size") @DefaultValue("2") int size,
                                   @QueryParam("order") @DefaultValue("sku") String order,
                                   @QueryParam("direction") @DefaultValue("ASC") Direction direction) {

        LOGGER.info("Find all tools, page: " + page + ", size: " + size + ", order: " + order + ", direction: " + direction);
        var pageRequest = PageRequest.ofPage(page).size(size);
        return toolRepository.findAll(pageRequest, Order.by(Sort.of(order, direction, false))).content();
    }

    @POST
    public void save(Tool tool) {
        LOGGER.info("Save tool: " + tool);
        toolRepository.save(tool);
    }

    @GET
    @Path("/{id}")
    public Tool findById(@PathParam("id") String id) {
        LOGGER.info("Find tool by id: " + id);
        return toolRepository.findById(id).orElseThrow(() -> new WebApplicationException("Tool not found", Response.Status.NOT_FOUND));
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") String id) {
        LOGGER.info("Delete tool by id: " + id);
        toolRepository.deleteById(id);
    }


}
