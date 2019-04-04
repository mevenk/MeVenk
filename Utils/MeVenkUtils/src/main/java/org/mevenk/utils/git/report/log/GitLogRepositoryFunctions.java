/**
 * 
 */
package org.mevenk.utils.git.report.log;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

/**
 * @author vkolisetty
 *
 */
abstract class GitLogRepositoryFunctions {

	/**
	 * 
	 */
	private GitLogRepositoryFunctions() {
		// Prevent Object instantiation
	}

	/**
	 * @param gitDir
	 * @return
	 * @throws IOException
	 */
	static final Repository getRepository(File gitDir) throws IOException {
		return new FileRepositoryBuilder().setGitDir(gitDir).setMustExist(true).build();
	}

}
