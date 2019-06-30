## 本周已完成任务
- 本周主要工作 
  - 阅读两篇论文的目录与基本框架从整体上了解整篇论文大概讲了什么

- 论文一：Dynamic Computation Offloading for Mobile Cloud Computing: A Stochastic Game-Theoretic Approach
  - 1.INTRODUCTION
  - 2.RELATED WORK
  - 3.SYSTEM MODEL
    + 3.1 Dynamics of Mobile Users’ Activeness and Wireless Channels
    + 3.2 Communication Model for Active Mobile Users
    + 3.3 Computation Model for Active Mobile Users
      - 3.3.1 Computation Cost When Choosing to Perform Cloud Computing
      - 3.3.2 Computation Cost When Choosing to Perform Local Computing
  - 4.STOCHASTIC COMPUTATION OFFLOADING GAME
    + 4.1 Game Models
      - 4.1.1 Static Case
      - 4.1.2 Dynamic Case
    + 4.2 Analysis of Nash Equilibrium
  - 5.PERFORMANCE ANALYSIS OF NASEQUILIBRIUM
    + 5.1 Metric I: System-Wide Computation Cost
    + 5.2 Metric II: Beneficial Cloud Computing Users
  - 6.MULTI-AGENT STOCHASTIC LEARNING UNDER DYNAMIC ENVIRONMENT
    + 6.1 Proposed Multi-Agent Stochastic Learning Algorithm
    + 6.2 Convergence Properties of MASL-Algorithm
  - 7.SIMULATION RESULTS
    + 7.1 Convergence Analysis
    + 7.2 Performance Evaluation
  - 8.CONCLUSION  
  
- 论文二：MAUI: Making Smartphones Last Longer with Code Offload
  - 1.INTRODUCTION
  - 2.THE NEED FOR REMOTE EXECUTION IN TODAY’S MOBILE LANDSCAPE
    - 2.1 How Severe is the Energy Problem onToday’s Mobile Handheld Devices?
    - 2.2 How Energy Efficient is 3G for CodeOffload?
    - 2.3 How Sensitive is the Energy Consumption of Code Offload to the RTT to the Server?
  - 3.MAUI SYSTEM ARCHITECTURE
  - 4.PROGRAM PARTITIONING
    - 4.1 Partitioning .NET Applications
      - 4.1.1 Executing the Same Code on Different CPU Architectures
      - 4.1.2 Extracting Remoteable Methods UsingReflection
      - 4.1.3 Identifying the State Needed for Remote Execution Using Type-Safety and Reflection
      - 4.1.4 Performing Code Offload
    - 4.2 Handling Failures
    - 4.3 Additional Program Modifications CanBring Performance Benefits
  - 5.MAUI PROFILER
    - 5.1 Device Profiling
    - 5.2 Program Profiling
      - 5.2.1 Profiling Overhead
    - 5.3 Network Profiling
   - 6.MAUI SOLVER
   - 7.EVALUATION
    - 7.1 Methodology
    - 7.2 Macrobenchmarks
      - 7.2.1 How Much Energy Does MAUI Save For Mobile Applications?
      - 7.2.2 How Much Does MAUI Improve thePerformance of Mobile Applications?
      - 7.2.3 Can MAUI Run Resource-IntensiveApplications?
    - 7.3 Microbenchmarks
      - 7.3.1 What is the Overhead of MAUI’s Solver?
      - 7.3.2 Does MAUI Require a Global View of theProgram to Identify Offload Opportunities?
      - 7.3.3 How Effective are Incremental Deltas at Reducing MAUI’s Data Transfer Overhead?
      - 7.3.4 Does the MAUI Solver Adapt to ChangingNetwork Conditions and CPU Costs?
  - 8.RELATED WORK
    - 8.1 Program Partitionin
    - 8.2 Process and VM Migration
  - 9.CONCLUSIONS
