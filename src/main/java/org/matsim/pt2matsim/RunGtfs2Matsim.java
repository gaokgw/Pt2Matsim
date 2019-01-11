package org.matsim.pt2matsim;

import org.matsim.pt2matsim.run.*;
import java.io.File;


public class RunGtfs2Matsim {

    private static final String inputfolder = "test/GTFS_standard_data/";
        private static final String outputfolder = "output/Gtfs2Matsim/";
    private static final String countyEPSG = "EPSG:2446";


    public static void main(String[] args) {
        prepare();
        // 1. Convert a gtfs schedule to an unmapped transit schedule
        gtfsToSchedule();
        // OR a hafas schedule to an unmapped transit schedule
        // hafasToSchedule();

    }

    public static void prepare() {
        // Create output folder if not existing:
        new File(outputfolder).mkdirs();
    }

    /**
     * 	1. A GTFS or HAFAS Schedule or a OSM map with information on public transport
     * 	has to be converted to an unmapped MATSim Transit Schedule.
     *
     * 	Here as a first example, the GTFS-schedule of GrandRiverTransit, Waterloo-Area, Canada, is converted.
     */
    public static void gtfsToSchedule() {
        String[] gtfsConverterArgs = new String[]{
                // [0] folder where the gtfs files are located
                inputfolder,
                // [1] which service ids should be used. One of the following:
                //		dayWithMostTrips, date in the format yyyymmdd, , dayWithMostServices, all
                "dayWithMostTrips",
                // [2] the output coordinate system. Use WGS84 for no transformation.
                countyEPSG,
                // [3] output transit schedule file
                outputfolder + "schedule_gtfs.xml",
                //outputfolder + "schedule_unmapped.xml.gz",
                // [4] output default vehicles file (optional)
                outputfolder + "vehicles_gtfs.xml",
                //outputfolder + "vehicles_unmapped.xml.gz",
        };
        Gtfs2TransitSchedule.main(gtfsConverterArgs);
    }

    // Here as a second example, the HAFAS-schedule of the BrienzRothornBahn, Switzerland, is
    // converted.
    /*

    public static void hafasToSchedule() {
        String[] hafasConverterArgs = new String[]{
                // [0] hafasFolder
                test + "BrienzRothornBahn-HAFAS/",
                // [1] outputCoordinateSystem
                "EPSG:2056",
                // [2] outputScheduleFile
                outputUnmapped + "schedule_hafas.xml.gz",
                // [3] outputVehicleFile
                outputUnmapped + "vehicles_hafas.xml"
        };
        try {
            Hafas2TransitSchedule.main(hafasConverterArgs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    */



}
