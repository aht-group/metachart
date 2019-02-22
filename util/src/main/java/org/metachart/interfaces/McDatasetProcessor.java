package org.metachart.interfaces;

import org.metachart.exception.McProcessingException;
import org.metachart.xml.chart.Ds;

public interface McDatasetProcessor
{
	Ds process(Ds ds) throws McProcessingException;
}
