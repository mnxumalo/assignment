package org.mthu.interstellar.business;

import java.util.Comparator;

import org.mthu.interstellar.model.Vertex;

public class PriorityQueueComparator implements Comparator<Vertex> {
	
    @Override
    public int compare(Vertex vertex1, Vertex vertex2) {
        if(vertex1.getDistance() < vertex2.getDistance())
            return  -1;
        if(vertex1.getDistance() > vertex2.getDistance())
            return 1;
        else
            return 0;
    }
}
