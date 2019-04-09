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
abstract class GitLogFilter {

	/**
	 * 
	 */
	public GitLogFilter() {
		// Prevent Object instantiation
	}

	/**
	 * 
	 * @return
	 */
	static final RevFilter none() {
		return RevFilter.NONE;
	}

	/**
	 * 
	 * @return
	 */
	static final RevFilter noMerges() {
		return RevFilter.NO_MERGES;
	}

	/**
	 * 
	 * @return
	 */
	static final RevFilter mergeBase() {
		return RevFilter.MERGE_BASE;
	}

	/**
	 * 
	 * @return
	 */
	static final RevFilter onlyMerges() {
		return RevFilter.ONLY_MERGES;
	}

	/**
	 * 
	 * @param before
	 * @return
	 */
	static final RevFilter before(Date before) {
		return CommitTimeRevFilter.before(before);
	}

	/**
	 * 
	 * @param afer
	 * @return
	 */
	static final RevFilter after(Date after) {
		return CommitTimeRevFilter.after(after);
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
