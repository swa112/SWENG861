/**
 * 
 */
package sweng861.hls.protocolanalyzer.rule;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.functors.EqualPredicate;

import sweng861.hls.protocolanalyzer.evaluator.ErrorType;
import sweng861.hls.protocolanalyzer.file.HLSMediaFile;
import sweng861.hls.protocolanalyzer.file.MediaFileTagType;

/**
 * @author Scott
 *
 */
class FileHasCorrectNumberOfTagsRule extends AbstractMediaFileRule {

	/* (non-Javadoc)
	 * @see sweng861.hls.protocolanalyzer.rule.HLSRule#runRuleCheck(sweng861.hls.protocolanalyzer.file.HLSMediaFile)
	 */
	public void runRuleCheck(HLSMediaFile file) {
		List<MediaFileTagType> tagList = file.getTagList();
		List<MediaFileTagType> tagsThatMustBeUniquePerFile = MediaFileTagType.getTagsThatMustBeUniquePerFile();
		for (MediaFileTagType uniqueTag : tagsThatMustBeUniquePerFile){
			Predicate predicate = EqualPredicate.getInstance(uniqueTag);
			int matches = CollectionUtils.countMatches(tagList, predicate);
			if(matches > 1){
				super.addToErrorLog(file, 
						ErrorType.TAG_THAT_MUST_BE_UNIQUE_APPERS_MORE_THAN_ONCE.getSeverity(), 
						String.format(ErrorType.TAG_THAT_MUST_BE_UNIQUE_APPERS_MORE_THAN_ONCE.getMessageFormat(), uniqueTag.name(), matches));
			}
		}

	}

}
