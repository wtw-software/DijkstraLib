DijkstraLib
===========

[![](https://jitpack.io/v/wtw-software/DijkstraLib.svg)](https://jitpack.io/#wtw-software/DijkstraLib)

Dijkstra algorithm implementation for Android. Used to calculate shortest path/distance between given polygons.

## Usage

Vertex may contain any type of payload object. 
The payload needs to implement ```equals(..)``` (to assess equality between vertices) and ```hashCode(..)``` to guarantee that payload objects are compared by content, and not by reference. 

```
Vertex<Integer> vertexFrom = new Vertex<>(zoneFrom);
Vertex<Integer> vertexTo = new Vertex<>(zoneTo);

// Finding length of path (number of steps) between nodes: 
int pathLength = new DijkstraAlgorithm(new Graph(edges)).execute(vertexFrom).getShortestPathLength(vertexFrom, vertexTo);

// Finding weighted distance between nodes.
int pathWeightedDistance = new DijkstraAlgorithm(new Graph(edges)).execute(vertexFrom).getDistance(vertexTo);
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
