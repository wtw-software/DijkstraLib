DijkstraLib
===========

Dijkstra algorithm implementation for Android. Used to calculate shortest path/distance between given polygons.

## Maven
```
<dependency>
    <groupId>no.wtw.android</groupId>
    <artifactId>dijkstra-algorithm</artifactId>
    <type>apklib</type>
    <version>1.3</version>
</dependency>
```

## Gralde
```
dependencies {
    ...
    compile 'no.wtw.android:dijkstra-algorithm:1.3'
    ...
}
```

## Usage

1. Subclass DijkstraCalculation.
```public class MyDijkstra extends DijkstraCalculation```

2. Override 2 abstract methods and provide your own implementation.
```public abstract void setUpDataFromDatabase();
   public abstract void setUpData();```

3. Invoke method calculateShortestPathBetween or calculateShortestPathWithWeight
   with vertices ids as arguments (from, to).
```int distance = this.mDijkstra.calculateShortestPathBetween(from, to);```

   Or use implemented AsyncTask to calculate distance in background thread.
   by providing reference to your instance of DijkstraCalculation subclass.
```dijkstraCalculationAsyncTask = new DijkstraCalculationAsyncTask(MyDijkstra.getInstance(this), this);
   dijkstraCalculationAsyncTask.execute(from, to);```

## Example

MyDijkstra class implementation

```
public class MyDijkstra extends DijkstraCalculation{
    private static final String TAG = MyDijkstra.class.getSimpleName();
    public static MyDijkstra getInstance(Activity activity) {
        if(instance == null) {
            instance = new MyDijkstra(activity);
        }
        return instance;
    }

    private MyDijkstra(Activity activity) {
        super(activity);
    }

    @Override
    public void setUpDataFromDatabase() {
        mNodes = new ArrayList<Vertex>();
        mEdges = new ArrayList<Edge>();
        GeoDatabaseManager geoDatabaseManager = GeoDatabaseManager.getInstance(mActivity);
        ArrayList<GeoData.GeoBusZone> geoZoneArray = geoDatabaseManager.getGeoZonesWithStops();
        ArrayList<GeoData.GeoZoneRelation> geoZoneRelationArray = geoDatabaseManager.getAllZoneRelations();
        createAllEdgesAndVertices(geoZoneArray,geoZoneRelationArray);
    }

    @Override
    public void setUpData() {

    }

    public void setUpData(ArrayList<GeoData.GeoBusZone> geoZoneArray,ArrayList<GeoData.GeoZoneRelation> geoZoneRelationArray) {
        mNodes = new ArrayList<Vertex>();
        mEdges = new ArrayList<Edge>();
        createAllEdgesAndVertices(geoZoneArray,geoZoneRelationArray);
    }

    private void createAllEdgesAndVertices(ArrayList<GeoData.GeoBusZone> geoZoneArray,ArrayList<GeoData.GeoZoneRelation> geoZoneRelationArray) {
        long start = System.currentTimeMillis();

        for (GeoData.GeoBusZone geoZone : geoZoneArray) {
            mNodes.add(new Vertex(geoZone.getZoneid(), geoZone.getName()));
        }

        for (GeoData.GeoZoneRelation geoZoneRelation : geoZoneRelationArray) {
            Vertex vertexFrom = getVertexFromListById(geoZoneRelation.getFromzoneid());
            Vertex vertexTo = getVertexFromListById(geoZoneRelation.getTozoneid());
            if((vertexFrom != null) && (vertexTo != null)) {
                mEdges.add(new Edge(new String("edge_"+geoZoneRelation.getFromzoneid()+"_"+geoZoneRelation.getTozoneid()), vertexFrom, vertexTo, geoZoneRelation.getDistance()));
                mEdges.add(new Edge(new String("edge_"+geoZoneRelation.getTozoneid()+"_"+geoZoneRelation.getFromzoneid()), vertexTo, vertexFrom, geoZoneRelation.getDistance()));
            }
        }
        long stop = System.currentTimeMillis();
        float diff = ((float)(stop - start))/1000.0f;
        Log.d(TAG, "loading vertex and edge time =" + diff);
    }
}

```

## License

    Copyright 2014 WTW AS

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
