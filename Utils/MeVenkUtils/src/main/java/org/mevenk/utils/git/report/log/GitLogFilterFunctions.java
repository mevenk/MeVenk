/**
 * 
 */
package org.mevenk.utils.git.report.log;

import java.util.Collection;
import java.util.Date;

import org.eclipse.jgit.revwalk.filter.AndRevFilter;
import org.eclipse.jgit.revwalk.filter.CommitTimeRevFilter;
import org.eclipse.jgit.revwalk.filter.MaxCountRevFilter;
import org.eclipse.jgit.revwalk.filter.OrRevFilter;
import org.eclipse.jgit.revwalk.filter.RevFilter;

/**
 * @author vkolisetty
 *
 */
abstract class GitLogFilterFunctions {

	/**
	 * 
	 */
	public GitLogFilterFunctions() {
		// Prevent Object instantiation
	}

	/**
	 * @param since
	 * @param until
	 * @return
	 */
	static final RevFilter between(Date since, Date until) {
		return CommitTimeRevFilter.between(since, until);
	}

	/**
	 * 
	 * @param maxNoOfCommits
	 * @return
	 */
	static final RevFilter maxNoOfCommits(int maxNoOfCommits) {
		return MaxCountRevFilter.create(maxNoOfCommits);
	}

	/**
	 * 
	 * @param revFilters
	 * @return
	 */
	static final RevFilter and(RevFilter[] revFilters) {
		return AndRevFilter.create(revFilters);
	}

	/**
	 * 
	 * @param revFilters
	 * @return
	 */
	static final RevFilter and(Collection<RevFilter> revFilters) {
		return AndRevFilter.create(revFilters);
	}

	/**
	 * 
	 * @param revFilters
	 * @return
	 */
	static final RevFilter or(RevFilter[] revFilters) {
		return OrRevFilter.create(revFilters);
	}

	/**
	 * 
	 * @param revFilters
	 * @return
	 */
	static final RevFilter or(Collection<RevFilter> revFilters) {
		return OrRevFilter.create(revFilters);
	}
}
