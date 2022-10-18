package fr.laurentvrevin.mareu.DI;

import fr.laurentvrevin.mareu.service.DummyMareuApiService;
import fr.laurentvrevin.mareu.service.MareuApiService;

public class DI {
    private static MareuApiService testservice = (MareuApiService) new DummyMareuApiService();

    /**
     * Get an instance on @{@link MareuApiService}
     * @return
     */
    public static MareuApiService getMeetingsApiService() {
        return testservice;
    }


    /**
     * Get always a new instance on @{@link MareuApiService}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static MareuApiService getNewInstanceApiService() {
        testservice = new DummyMareuApiService();
        return testservice;

    }


}
