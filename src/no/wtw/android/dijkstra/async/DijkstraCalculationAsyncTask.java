package no.wtw.android.dijkstra.async;

import no.wtw.android.dijkstra.DijkstraCalculation;
import no.wtw.android.dijkstra.listener.DijkstraCalculationListener;
import android.os.AsyncTask;

public class DijkstraCalculationAsyncTask extends AsyncTask<Integer, Void, Integer>{
	private static final String TAG = DijkstraCalculationAsyncTask.class.getSimpleName();
    private static final int DEFAULT_VALUE = 1;
    private static final int MINIMAL_PARAMS_SIZE = 2;
	private DijkstraCalculation mDijkstra;
	private DijkstraCalculationListener mDijkstraCalculationListener;
	
	public DijkstraCalculationAsyncTask(DijkstraCalculation dijkstra, DijkstraCalculationListener dijkstraCalculationListener) {
		this.mDijkstra = dijkstra;
		this.mDijkstraCalculationListener = dijkstraCalculationListener;
	}
	
	@Override
	protected Integer doInBackground(Integer... params) {
		if(params.length >= MINIMAL_PARAMS_SIZE) {
			int distance = this.mDijkstra.calculateShortestPathBetween(params[0], params[1]);
			return distance;
		}
		return DEFAULT_VALUE;
	}

	@Override
	protected void onPostExecute(Integer result) {
		super.onPostExecute(result);
		if((mDijkstraCalculationListener != null) && (!isCancelled())) {
			mDijkstraCalculationListener.onCalculationFinished(result.intValue());
		}
	}
}