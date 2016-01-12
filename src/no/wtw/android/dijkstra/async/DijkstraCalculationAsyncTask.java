package no.wtw.android.dijkstra.async;

import android.os.AsyncTask;
import no.wtw.android.dijkstra.DijkstraCalculation;
import no.wtw.android.dijkstra.listener.DijkstraCalculationListener;
import no.wtw.android.dijkstra.model.Vertex;

public class DijkstraCalculationAsyncTask<T> extends AsyncTask<T, Void, Integer> {

    private static final String TAG = DijkstraCalculationAsyncTask.class.getSimpleName();
    private static final int DEFAULT_VALUE = 1;
    private static final int MINIMAL_PARAMS_SIZE = 2;
    private DijkstraCalculation<T> dijkstra;
    private DijkstraCalculationListener dijkstraCalculationListener;

    public DijkstraCalculationAsyncTask(DijkstraCalculation<T> dijkstra, DijkstraCalculationListener dijkstraCalculationListener) {
        this.dijkstra = dijkstra;
        this.dijkstraCalculationListener = dijkstraCalculationListener;
    }

    @Override
    protected Integer doInBackground(T... params) {
        if (params.length >= MINIMAL_PARAMS_SIZE) {
            int distance = this.dijkstra.calculateShortestPathBetween(params[0], params[1]);
            return distance;
        }
        return DEFAULT_VALUE;
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        if ((dijkstraCalculationListener != null) && (!isCancelled())) {
            dijkstraCalculationListener.onCalculationFinished(result.intValue());
        }
    }
}