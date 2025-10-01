package com.crio.qcontest.services;

 import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import javax.management.RuntimeErrorException;
import com.crio.qcontest.entities.Contest;
import com.crio.qcontest.entities.Contestant;
import com.crio.qcontest.entities.DifficultyLevel;
import com.crio.qcontest.entities.Question;
import com.crio.qcontest.entities.User;
import com.crio.qcontest.repositories.IContestRepository;
import com.crio.qcontest.repositories.IContestantRepository;
import com.crio.qcontest.repositories.IQuestionRepository;
import com.crio.qcontest.repositories.IUserRepository;

public class ContestService{
    private final IContestantRepository contestantRepository;
    private final IContestRepository contestRepository;
    private final IQuestionRepository questionRepository;
    private final IUserRepository userRepository;

    public ContestService(IContestantRepository contestantRepository, IContestRepository contestRepository,
            IQuestionRepository questionRepository, IUserRepository userRepository) {
        this.contestantRepository = contestantRepository;
        this.contestRepository = contestRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    /**
     * Creates a new contest with specified parameters.
     * @param name Name of the contest.
     * @param level Difficulty level of the contest.
     * @param userId ID of the user creating the contest.
     * @param numOfQuestions Number of questions in the contest.
     * @return Created Contest object.
     * @throws RuntimeException if user is not found or requested questions cannot be fulfilled.
     */
    public Contest createContest(String name, DifficultyLevel level, Long userId, Integer numOfQuestions) {


         // Validate the user by userId using lambda expression
    User user = userRepository.findById(userId).orElseThrow(() -> 
    new RuntimeException("User with an id: " + userId + " not found!"));

    //   // Validate the user without using lamda expression and checking the optional user is empty or not.
    // Optional<User> userOptional = userRepository.findById(userId);
    // if (userOptional.isEmpty()) {
    //     throw new RuntimeException("User not found with ID: " + userId);
    // }
    // User user = userOptional.get();
 
     // Fetch questions based on the specified difficulty level
     List<Question> questions = questionRepository.findByDifficultyLevel(level);
 
     // Check if there are enough questions to fulfill the contest requirement
     if (questions.size() < numOfQuestions) {
         throw new RuntimeException("Requested number of questions: " + numOfQuestions+" cannot be fulfilled!");
     }
 
     // Select random questions for the contest
     List<Question> selectedQuestions = pickRandomQuestions(questions, numOfQuestions);
 
     // Create a new Contest object
     Contest contest = new Contest(name, level, user, selectedQuestions);
 
     // Save the contest to the repository
     return contestRepository.save(contest);
    }

    // https://www.codespeedy.com/how-to-randomly-select-items-from-a-list-in-java/
    private List<Question> pickRandomQuestions(List<Question> questionList, Integer numQuestions){
        Random rand = new Random(); // object of Random class.
       //temporary list to hold selected items.
        List<Question> tempList = new ArrayList<>(); 
        for (int i = 0; i < numQuestions; i++) { 
            int randomIndex = rand.nextInt(questionList.size());
            // the loop check on repetition of elements
            while(tempList.contains(questionList.get(randomIndex))){
                randomIndex = rand.nextInt(questionList.size());
            }
            tempList.add(questionList.get(randomIndex)); 
        } 
        return tempList; 
    }

    /**
     * Retrieves a list of contests filtered by difficulty level.
     * @param level Difficulty level filter (can be null).
     * @return List of contests filtered by difficulty level.
     */
    public List<Contest> listContests(DifficultyLevel level) {

        // Fetch all contests from the repository
    List<Contest> contests = contestRepository.findAll();

    List<Contest> filteredContests = new ArrayList<>();

    // If level is provided, filter contests by difficulty level
    if (level != null) {
        for (Contest contest : contests) {
            if (contest.getLevel() == level) {
                filteredContests.add(contest);
            }
        }
        return filteredContests;
    }

    // If no level filter is provided, return all contests
    return contests;
    }

    /**
     * Registers a user as a contestant for a contest.
     * @param contestId ID of the contest.
     * @param userId ID of the user registering for the contest.
     * @return Registered Contestant object.
     * @throws RuntimeException if contest or user is not found.
     */
    public Contestant attendContest(Long contestId, Long userId) {

 
              // Validate the contest with use of Lambda expression
      Contest contest = contestRepository.findById(contestId).orElseThrow(() -> 
      new RuntimeException("Contest with an id " + contestId + " not found!")
  );

             // Validate the user with use of lambda expression
  Optional<User> user = userRepository.findById(userId);
  if(!user.isPresent()){
    throw new RuntimeException("User with an id " + userId + " not found!");
  }

    // Check if the user is already a contestant in the contest
    List<Contestant> contestants = contestantRepository.findByContestId(contestId);
    for (Contestant contestant : contestants) {
        if (contestant.getUser().getId().equals(userId)) {
            throw new RuntimeException("User is already registered for the contest");
        }
    }

    // Register the user as a contestant
    Contestant contestant = new Contestant(user.get(), contest);
    return contestantRepository.save(contestant);
}

    /**
     * Withdraws a contestant from a contest.
     * @param contestId ID of the contest.
     * @param userId ID of the user withdrawing from the contest.
     * @return True if contestant is successfully withdrawn, false otherwise.
     * @throws RuntimeException if contestant is not found.
     */
    public Boolean withdrawContest(Long contestId, Long userId) {
        Optional<Contestant> contestant = contestantRepository.findById(contestId, userId);

        if(!contestant.isPresent()){
            throw new RuntimeException("Contestant not found!");
        }
        contestantRepository.deleteById(contestId, userId);
        return true;
    }

    /**
     * Executes a contest by selecting random questions for contestants and updating scores.
     * @param contestId ID of the contest.
     * @param userId ID of the user running the contest (contest creator).
     * @throws RuntimeException if contest is not found or user is not the creator.
     */
    public void runContest(Long contestId, Long userId) {
        // Check if contest is valid as per the required conditions.
        Contest contest = contestRepository.findById(contestId).orElseThrow(() -> new RuntimeException("Contest with an id "+contestId+" not found!"));
        if(!contest.getCreator().getId().equals(userId)){
            throw new RuntimeException("Only the contest creator can run the contest!");
        }
        // Get all the contestants who registered for the given contest.
        List<Contestant> contestantList = contestantRepository.findByContestId(contestId);
        // For each contestant,
        contestantList.forEach((contestant)-> {
            // Select random questions from the contest which will be considered as solved by the contestant.
            List<Question> solvedQuestionsList = pickRandomQuestions(contest.getQuestions(),getRandomNumberInRange(0,contest.getQuestions().size()));
            // Store the solved questions in the contestant.
            solvedQuestionsList.forEach((question)->{
                contestant.addQuestion(question);
            });
            User user = contestant.getUser();
            // Generate new totalScore for the user as per the recently solved questions.
            int newScore = contestant.getTotalScore() + user.getScore() - contest.getLevel().getWeight();
            // Update the totalscore of the user.
            user.setScore(newScore);
        });
    }

    // https://mkyong.com/java/java-generate-random-integers-in-a-range/
    private int getRandomNumberInRange(int min, int max) {
		Random r = new Random();
		return r.ints(min, (max + 1)).findFirst().getAsInt();
	}

    /**
     * Retrieves contest history sorted by contestant scores.
     * @param contestId ID of the contest.
     * @return List of contestants sorted by total score in descending order.
     * @throws RuntimeException if contest is not found.
     */
    public List<Contestant> contestHistory(Long contestId) {
        
         // Validate the contest with use of Lambda expression
      Contest contest = contestRepository.findById(contestId).orElseThrow(() -> 
      new RuntimeException("Contest with an id " + contestId + " not found!")
  );
     // Fetch all contestants for the contest
     List<Contestant> contestants = contestantRepository.findByContestId(contestId);

     // Sort contestants by total score in descending order using lambda expressions
     return contestants.stream()
     .sorted((c1, c2) -> Integer.compare(c2.getTotalScore(), c1.getTotalScore()))
     .collect(Collectors.toList());

    
    //          // Validate the contest without lambda exression.
    //      Optional<Contest> contestOptional = contestRepository.findById(contestId);
    // if (contestOptional.isEmpty()) {
    //     throw new RuntimeException("Contest with an id " + contestId + " not found!");
    // }
    // Contest contest = contestOptional.get();
       
    // Fetch all contestants for the contest
  //  List<Contestant> contestants = contestantRepository.findByContestId(contestId);

    // // Sort contestants by total score in descending order using a basic sorting algorithm
    // for (int i = 0; i < contestants.size() - 1; i++) {
    //     for (int j = 0; j < contestants.size() - i - 1; j++) {
    //         if (contestants.get(j).getTotalScore() < contestants.get(j + 1).getTotalScore()) {
    //             // Swap
    //             Contestant temp = contestants.get(j);
    //             contestants.set(j, contestants.get(j + 1));
    //             contestants.set(j + 1, temp);
    //         }
    //     }
    // }
    // return contestants;

    }
}
