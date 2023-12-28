package org.metachart.interfaces;

import org.metachart.exception.McProcessingException;
import org.metachart.model.xml.chart.Ds;

public interface McDatasetProcessor
{
	Ds process(Ds ds) throws McProcessingException;
}
