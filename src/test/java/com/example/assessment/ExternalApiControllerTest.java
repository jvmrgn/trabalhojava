
import com.example.assessment.controllers.ExternalApiController;
import com.example.assessment.model.ResponseExternalApi;
import com.example.assessment.service.ExternalApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExternalApiControllerTest {

    private ExternalApiController externalApiController;

    @Mock
    private ExternalApiService externalApiService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        externalApiController = new ExternalApiController(externalApiService);
    }

    @Test
    public void testGetExternalById_ExistingId() {
        String existingId = "1";
        ResponseExternalApi mockResponse = new ResponseExternalApi(existingId);
        when(externalApiService.getExternalById(existingId)).thenReturn(mockResponse);

        ResponseEntity<ResponseExternalApi> response = externalApiController.getExternalById(existingId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
    }

    @Test
    public void testGetExternalById_NonExistingId() {
        String nonExistingId = "456";
        when(externalApiService.getExternalById(nonExistingId)).thenReturn(null);

        ResponseEntity<ResponseExternalApi> response = externalApiController.getExternalById(nonExistingId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetAllExternal_NotEmptyList() {
        ResponseExternalApi mockResponse = new ResponseExternalApi("1");
        List<ResponseExternalApi> mockList = Collections.singletonList(mockResponse);
        when(externalApiService.getAllExternal()).thenReturn(mockList);

        ResponseEntity<List<ResponseExternalApi>> response = externalApiController.getAllExternal();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockList, response.getBody());
    }

    @Test
    public void testGetAllExternal_EmptyList() {
        when(externalApiService.getAllExternal()).thenReturn(Collections.emptyList());

        ResponseEntity<List<ResponseExternalApi>> response = externalApiController.getAllExternal();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testConsumeExternalAPI_Success() {
        when(externalApiService.consumeExternalAPI()).thenReturn("Salvo com o id: 1");

        ResponseEntity<String> response = externalApiController.consumeExternalAPI();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Salvo com o id: 1", response.getBody());
    }

    @Test
    public void testConsumeExternalAPI_Error() {
        when(externalApiService.consumeExternalAPI()).thenReturn("Erro ao consumir a API externa");

        ResponseEntity<String> response = externalApiController.consumeExternalAPI();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro ao consumir a API externa", response.getBody());
    }
}
